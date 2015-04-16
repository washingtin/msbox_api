package com.msbox.api.service;

import com.msbox.api.dao.active.ActiveMapper;
import com.msbox.api.model.Active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 15-3-31.
 */
@Service
public class ActiveService {
    @Autowired
    private ActiveMapper activeMapper;
}
