package com.co1248.monitoringsb.controlloer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co1248.monitoringsb.dto.Host;
import com.co1248.monitoringsb.host.HostService;

@RestController
public class HostController {
	private final HostService hostService = null;
	@GetMapping("/")
	public String test () {
        return "연결이 정상 작동 됩니다.";
    }

	//조회
	@GetMapping("/host/{id}")
	public Host getHost (@PathVariable(value = "id") String id) {
		System.out.println("=====>getHost 실행");
    	Host host = hostService.getHost(id);
        return host;
    }
	
	//리스트조회
	@GetMapping("/host/all")
	public List<Host> getHostList () {
		System.out.println("=====>getHostList 실행");
    	List<Host> hostlist = hostService.getHostList();
        return hostlist;
    }

	//등록
	@PostMapping("/add")
	public void insertHost (@RequestBody Host vo) {
		System.out.println("=====>insertHost 실행");
    	hostService.insertHost(vo);
    }

	//수정
	@PutMapping("/host/{id}")
	public void updateHost (@RequestBody Host vo) {
		System.out.println("=====>updateHost 실행");
    	hostService.updateHost(vo);
    }
	
	//삭제
	@DeleteMapping("/host/{id}")
	public void deleteHost (@PathVariable(value = "id") String id) {
		System.out.println("=====>deleteHost 실행");
    	hostService.deleteHost(id);
    }
}
