package com.abqdesibites.service;

import com.abqdesibites.dto.ContactRequestDto;
import com.abqdesibites.model.ContactInquiry;
import com.abqdesibites.repository.ContactInquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactInquiryRepository contactRepository;

    @Transactional
    public void saveInquiry(ContactRequestDto request) {
        ContactInquiry inquiry = new ContactInquiry();
        inquiry.setName(request.name());
        inquiry.setEmail(request.email());
        inquiry.setPhone(request.phone());
        inquiry.setMessage(request.message());
        contactRepository.save(inquiry);
    }
}
