package com.co1248.monitoringsb.host;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co1248.monitoringsb.entity.Host;
import com.co1248.monitoringsb.repository.HostRepository;

@Service
public class HostService {
	@Autowired
	public HostRepository hostRepository;

	public List<Host> getHostList() {
		return hostRepository.findAll();
	}

	public Host getHost(String id) {
		return hostRepository.getById(id);
	}

	public void insertHost(Host vo) {
		hostRepository.save(vo);
	}

	public void updateHost(Host vo) {
		hostRepository.save(vo);
	}

	public void deleteHost(String id) {
		hostRepository.deleteById(id);
		
	}

}
