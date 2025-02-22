package com.ticket.service;

import com.ticket.dto.AdminDTO;
import com.ticket.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin login(AdminDTO adminDto);
}
