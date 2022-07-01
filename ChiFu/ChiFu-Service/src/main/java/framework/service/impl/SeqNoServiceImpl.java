package framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import framework.dao.SeqNoRepository;
import framework.model.SeqNo;
import framework.service.SeqNoService;

@Service
public class SeqNoServiceImpl implements SeqNoService {
	@Autowired
	SeqNoRepository seqNoRepository;

	@Override
	public int findByModuleNo(String moduleNo) {
		int seq = 1;
		Optional<SeqNo> seqOptional = seqNoRepository.findByModuleNo(moduleNo);
		if (seqOptional.isPresent()) {
			seq = seqOptional.get().getSeq() + 1;
		}
		seqNoRepository.save(new SeqNo(moduleNo, seq));
		return seq;
	}

}