package com.co1248.monitoringsb.controlloer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co1248.monitoringsb.entity.Host;
import com.co1248.monitoringsb.host.HostService;

@RestController
public class HostController {
	@Autowired
	private HostService hostService;

	@GetMapping("/")
	public String test(HttpServletRequest request) {
		return "index";
	}

	// 조회
	@GetMapping("/host/{id}")
	public Host getHost(@PathVariable(value = "id") String id) {
		System.out.println("=====>getHost 실행");
		Host host = hostService.getHost(id);
		return host;
	}

	// 리스트조회
	@GetMapping("/host/all")
	public List<Host> getHostList() {
		System.out.println("=====>getHostList 실행");
		List<Host> hostlist = hostService.getHostList();
		return hostlist;
	}

	// 등록
	@PostMapping("/add")
	public String insertHost(@RequestBody Host vo) throws UnknownHostException {
		System.out.println("=====>insertHost 실행");
		if (hostService.getHostList().size() > 100) {
			return "등록된 호스트가 100명이 넘어 새로운 등록이 불가합니다.";
		} else {
			// name, address값 받아 전달
			InetAddress ia = InetAddress.getLocalHost();
			vo.setName(ia.getHostName());
			vo.setAddress(ia.getHostAddress());
			hostService.insertHost(vo);
			System.out.println("등록 완료");
			return "/host/all";
		}
	}

	// 수정
	@PutMapping("/host/{id}")
	public String updateHost(@RequestBody Host vo) throws UnknownHostException {
		System.out.println("=====>updateHost 실행");
		// id, name, address값 받아 전달
		String id = vo.getId();
		Host host = hostService.getHost(id);
		vo.setId(host.getId());
		InetAddress ia = InetAddress.getLocalHost();
		vo.setName(ia.getHostName());
		vo.setAddress(ia.getHostAddress());
		// 수정시간도 보내주기
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("Current Time Stamp: " + timestamp);
		vo.setModDate(timestamp);
		hostService.updateHost(vo);
		System.out.println("수정 완료");
		return "/host/all";
	}

	// 삭제
	@DeleteMapping("/host/{id}")
	public String deleteHost(@PathVariable(value = "id") String id) {
		System.out.println("=====>deleteHost 실행");
		hostService.deleteHost(id);
		System.out.println("삭제 완료");
		return "/host/all";
	}

	// 모니터링
	public String monitoring() throws IOException {
		System.out.println("=====>monitoring 실행");
		List<Host> hostlist = hostService.getHostList();
		for (Host vo2 : hostlist) {
			String id = vo2.getId();
			String name = vo2.getName();
			InetAddress iaddr = InetAddress.getByName(name);
			boolean reachable = iaddr.isReachable(2000);
			if (reachable) {
				System.out.println(name + "alive!");
				vo2.setId(id);
				vo2.setAlive("Y");
				Long datetime = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(datetime);
				vo2.setAliveDate(timestamp);
				hostService.updateHost(vo2);
				System.out.println("alive 수정 완료");
			} else {
				System.out.println(name + "dead..");
				vo2.setId(id);
				vo2.setAlive("N");
				hostService.updateHost(vo2);
				System.out.println("dead 수정 완료");
			}
		}
		return "/host/all";
	}
}
