package com.co1248.monitoringsb.host;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co1248.monitoringsb.dto.Host;

public interface HostService {
	
    public List<Host> getHostList();

	public Host getHost(String id);

	public void insertHost(Host vo);

	public void updateHost(Host vo);

	public void deleteHost(String id);
}
