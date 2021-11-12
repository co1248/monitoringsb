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

	// ��ȸ
	@GetMapping("/host/{id}")
	public Host getHost(@PathVariable(value = "id") String id) {
		System.out.println("=====>getHost ����");
		Host host = hostService.getHost(id);
		return host;
	}

	// ����Ʈ��ȸ
	@GetMapping("/host/all")
	public List<Host> getHostList() {
		System.out.println("=====>getHostList ����");
		List<Host> hostlist = hostService.getHostList();
		return hostlist;
	}

	// ���
	@PostMapping("/add")
	public String insertHost(@RequestBody Host vo) throws UnknownHostException {
		System.out.println("=====>insertHost ����");
		if (hostService.getHostList().size() > 100) {
			return "��ϵ� ȣ��Ʈ�� 100���� �Ѿ� ���ο� ����� �Ұ��մϴ�.";
		} else {
			// name, address�� �޾� ����
			InetAddress ia = InetAddress.getLocalHost();
			vo.setName(ia.getHostName());
			vo.setAddress(ia.getHostAddress());
			hostService.insertHost(vo);
			System.out.println("��� �Ϸ�");
			return "/host/all";
		}
	}

	// ����
	@PutMapping("/host/{id}")
	public String updateHost(@RequestBody Host vo) throws UnknownHostException {
		System.out.println("=====>updateHost ����");
		// id, name, address�� �޾� ����
		String id = vo.getId();
		Host host = hostService.getHost(id);
		vo.setId(host.getId());
		InetAddress ia = InetAddress.getLocalHost();
		vo.setName(ia.getHostName());
		vo.setAddress(ia.getHostAddress());
		// �����ð��� �����ֱ�
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("Current Time Stamp: " + timestamp);
		vo.setModDate(timestamp);
		hostService.updateHost(vo);
		System.out.println("���� �Ϸ�");
		return "/host/all";
	}

	// ����
	@DeleteMapping("/host/{id}")
	public String deleteHost(@PathVariable(value = "id") String id) {
		System.out.println("=====>deleteHost ����");
		hostService.deleteHost(id);
		System.out.println("���� �Ϸ�");
		return "/host/all";
	}

	// ����͸�
	public String monitoring() throws IOException {
		System.out.println("=====>monitoring ����");
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
				System.out.println("alive ���� �Ϸ�");
			} else {
				System.out.println(name + "dead..");
				vo2.setId(id);
				vo2.setAlive("N");
				hostService.updateHost(vo2);
				System.out.println("dead ���� �Ϸ�");
			}
		}
		return "/host/all";
	}
}
