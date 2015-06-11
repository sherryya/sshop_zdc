package com.tmount.business.pub.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.pub.dao.DbSequenceMapper;
import com.tmount.db.pub.dao.DbTimeMapper;
import com.tmount.db.pub.dto.DbSequence;
import com.tmount.db.pub.dto.DbTime;

@Service
public class PubService {

	@Autowired
	private DbTimeMapper dbTimeMapper;

	@Autowired
	private DbSequenceMapper dbSequenceMapper;

	public DbTime getDbTime() {
		return dbTimeMapper.selectDbTime();
	}

	public String getYesterdayStr() {

		return dbTimeMapper.selectYesterdayStr();
	}

	public String getTotalDateStr() {
		return dbTimeMapper.selectTotalDateStr();
	}

	public long updateSeqByName(String seqName) {
		DbSequence dbSequence = dbSequenceMapper.selectSeqByName(seqName);
		return dbSequence.getSeqValue();
	}

	public String randomString(int length) {
		if (length < 1) {
			return null;
		}

		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
				+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		// numbersAndLetters =
		// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
			// randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}

}
