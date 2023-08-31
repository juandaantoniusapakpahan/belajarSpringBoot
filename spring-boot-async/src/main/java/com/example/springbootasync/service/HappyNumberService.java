package com.example.springbootasync.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HappyNumberService {
    public boolean getHappy(int number){
        List<Integer> listNumber = new ArrayList<>();
        while (number != 1){
            if (listNumber.contains(number)){
                return false;
            }
            listNumber.add(number);
            int temp = 0;
            int lp =0;
            while (number > 0){
                int mod = number%10;
                temp +=  mod * mod;
                number/=10;
                if (lp == 0){

                }
            }
            number = temp;
        }
        return true;
    }
}
