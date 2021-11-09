package com.co1248.monitoringsb.host;

import java.util.List;

import org.springframework.stereotype.Service;

import com.co1248.monitoringsb.dto.HostVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public interface HostService {
	/* private final HostRepository hostRepository; */

    public List<HostVO> getHostList();
}
