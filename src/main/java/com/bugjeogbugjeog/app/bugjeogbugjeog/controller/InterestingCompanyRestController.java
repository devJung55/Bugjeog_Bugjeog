package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InterestingCompanyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InterestingCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class InterestingCompanyRestController {
    private final InterestingCompanyService interestingCompanyService;

    @DeleteMapping("specific/personalFavoriteList")
    public ResponseEntity<Void> deleteInterestingCompany(@RequestParam("businessId") Long businessId, @RequestParam("memberId") Long memberId) {
        interestingCompanyService.deleteInterestingCompany(businessId, memberId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("specific/personalFavoriteList")
    public ResponseEntity<Void> insertInterestingCompany(@RequestParam("businessId") Long businessId, @RequestParam("memberId") Long memberId) {
        interestingCompanyService.insertInterestingCompany(businessId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
