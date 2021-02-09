package com.cafs92.devolveai.controller;

import com.cafs92.devolveai.model.Loan;
import com.cafs92.devolveai.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @GetMapping
    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id){
        return loanRepository.findById(id).map(loan -> ResponseEntity.ok().body(loan)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loan addLoan(@RequestBody Loan loan){
        loan.setDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan, @PathVariable Long id){
        return loanRepository.findById(id).map(l ->{
                                                    l.setFriend(loan.getFriend());
                                                    l.setItem(loan.getItem());
                                                    loan.setDate(loan.getDate());
                                                    Loan updatedLoan = loanRepository.save(l);
                                                    return ResponseEntity.ok().body(updatedLoan);
                                                })
                                                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id){
        return loanRepository.findById(id).map(loan -> {
                                                        loanRepository.deleteById(id);
                                                        return ResponseEntity.ok().build();
                                                    })
                                                .orElse(ResponseEntity.notFound().build());
    }
}
