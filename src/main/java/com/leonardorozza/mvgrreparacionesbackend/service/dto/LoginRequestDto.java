package com.leonardorozza.mvgrreparacionesbackend.service.dto;

public record LoginRequestDto(
        String username,
        String password
) {}