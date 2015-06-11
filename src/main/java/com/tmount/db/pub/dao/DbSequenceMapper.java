package com.tmount.db.pub.dao;

import com.tmount.db.pub.dto.DbSequence;

public interface DbSequenceMapper {
    DbSequence selectSeqByName(String name);
}
