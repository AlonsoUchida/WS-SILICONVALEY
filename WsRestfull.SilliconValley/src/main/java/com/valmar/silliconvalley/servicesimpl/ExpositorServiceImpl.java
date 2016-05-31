package com.valmar.silliconvalley.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.dao.ExpositorDao;
import com.valmar.silliconvalley.model.Expositor;
import com.valmar.silliconvalley.services.ExpositorService;

@Service("expositorService")
@Transactional
public class ExpositorServiceImpl implements ExpositorService{

	@Autowired
	private ExpositorDao expositorDao;
	
	@Override
	public List<Expositor> listarExpositores() {
		return expositorDao.listarExpositores();
	}

}
