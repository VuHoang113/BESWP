package com.example.swp.service.impl;

import com.example.swp.repository.*;
import com.example.swp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;



}
