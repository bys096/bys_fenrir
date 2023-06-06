package com.fenrir.guruguru_spring.domain.reply.controller;

import com.fenrir.guruguru_spring.domain.reply.dto.ReplyCreateRequestDto;
import com.fenrir.guruguru_spring.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping()
    public void createReply(@Valid @RequestBody ReplyCreateRequestDto dto) {
        replyService.createReply(dto);
    }
}
