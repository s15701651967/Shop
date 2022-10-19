package com.bistu.store.service.impl;

import com.bistu.store.entity.Comment;
import com.bistu.store.entity.UserPurse;
import com.bistu.store.mapper.CommentMapper;
import com.bistu.store.service.ICommentService;
import com.bistu.store.service.ex.InsertException;
import com.bistu.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service//标记当前类是一个service类，加上该注解会将当前类自动注入到spring容器中,将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insert(Comment comment) {
        Date date =new Date();
        comment.setCreatedTime(date);
        comment.setModifiedTime(date);
        Integer rows=commentMapper.insert(comment);
        if(rows!=1){
            throw new InsertException("评论记录异常");
        }
    }

    @Override
    public void updateByBid(Integer uid, Integer bid, Integer oid, Integer pid, String reply) {
        Integer rows=commentMapper.updateByBid(uid, bid, oid, pid, reply);
        if(rows!=1){
            throw new UpdateException("评论记录更新异常");
        }
    }

    @Override
    public List<Comment> findByUid(Integer uid) {
        List<Comment> result = commentMapper.findByUid(uid);
        return result;
    }

    @Override
    public List<Comment> findByBid(Integer bid) {
        List<Comment> result = commentMapper.findByBid(bid);
        return result;
    }

    @Override
    public List<Comment> findByPid(Integer pid) {
        List<Comment> result = commentMapper.findByPid(pid);
        return result;
    }
}
