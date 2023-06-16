package com.fenrir.guruguru_spring.domain.reply.controller;

import com.fenrir.guruguru_spring.domain.reply.dto.ReplyCreateRequestDto;
import com.fenrir.guruguru_spring.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createReply(@Valid @RequestBody ReplyCreateRequestDto dto) {
        replyService.createReply(dto);
    }

}
