package com.example.springbootjwtmysql.service;


import com.example.springbootjwtmysql.entity.MoveZeroes;
import com.example.springbootjwtmysql.entity.MoveZeroesRequest;
import org.springframework.stereotype.Service;

@Service
public class MoveZeroesService {



    public MoveZeroes moveZeroes(MoveZeroesRequest moveZeroesRequest){
        int j = 0;
        for (int i=0; i<moveZeroesRequest.getNumbers().size(); i++){
            if (moveZeroesRequest.getNumbers().get(i)!= 0){
                int temp1 = moveZeroesRequest.getNumbers().get(i);
                int temp2 = moveZeroesRequest.getNumbers().get(j);
                moveZeroesRequest.getNumbers().set(i, temp2);
                moveZeroesRequest.getNumbers().set(j, temp1);
            }
            if (moveZeroesRequest.getNumbers().get(j)!=0){
                j++;
            }
        }
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.setNumbers(moveZeroesRequest.getNumbers());
        return moveZeroes;
    }
}
