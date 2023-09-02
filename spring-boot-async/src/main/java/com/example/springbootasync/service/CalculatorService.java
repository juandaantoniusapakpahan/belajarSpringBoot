package com.example.springbootasync.service;


import com.example.springbootasync.model.Calculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CalculatorService {

    @Async
    public CompletableFuture<Double> min(List<Double> numbers) throws  InterruptedException{
        double min = numbers.get(0);
        for (int i=1; i<numbers.size(); i++){
            if (min > numbers.get(i)){
                min = numbers.get(i);
            }
        }
        return CompletableFuture.completedFuture(min);
    }

    @Async
    public CompletableFuture<Double> max(List<Double> numbers) throws  InterruptedException{
        double max = numbers.get(0);
        for (int i=1; i<numbers.size(); i++){
            if (max < numbers.get(i)){
                max = numbers.get(i);
            }
        }
        return CompletableFuture.completedFuture(max);
    }

    @Async
    public CompletableFuture<Double> addition(List<Double> numbers) throws InterruptedException{
        double sum = 0;
        for (double i: numbers){
            sum+=i;
        }
        return CompletableFuture.completedFuture(sum);
    }

    @Async
    public CompletableFuture<Double> subtraction(List<Double> numbers) throws InterruptedException{
        double sub = 0;
        for (double i: numbers){
            sub-=i;
        }
        return CompletableFuture.completedFuture(sub);
    }

    @Async
    public CompletableFuture<Double> multiplication(List<Double> numbers) throws InterruptedException{
        double mul = 1;
        for (double i :numbers){
            mul*=i;
        }
        return CompletableFuture.completedFuture(mul);
    }

    @Async
    public CompletableFuture<Double> distribution(List<Double> numbers) throws InterruptedException{
        double dis = numbers.get(0);
        for (int i=1; i<numbers.size(); i++){
            dis/=numbers.get(i);
        }
        return CompletableFuture.completedFuture(dis);
    }

    public Calculator getCalculator(List<Double> numbers) throws Exception{
        Calculator calculator = new Calculator();
        CompletableFuture<Double> min = this.min(numbers);
        CompletableFuture<Double> max = this.max(numbers);
        CompletableFuture<Double> addition = this.addition(numbers);
        CompletableFuture<Double> subtraction = this.subtraction(numbers);
        CompletableFuture<Double> multiplication = this.multiplication(numbers);
        CompletableFuture<Double> distribution = this.distribution(numbers);
        CompletableFuture.allOf(min, max, addition, subtraction, multiplication, distribution);
        Thread.sleep(5l);
        calculator.setMin(min.get());
        calculator.setMax(max.get());
        calculator.setAddition(addition.get());
        calculator.setSubtraction(subtraction.get());
        calculator.setMultiplication(multiplication.get());
        calculator.setDistribution(distribution.get());
        return calculator;
    }
}
