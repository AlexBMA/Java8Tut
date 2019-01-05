package com.alex.lambdaExpresionExercises;

import java.util.List;

public class BasicCalculator<T> {

    public T sum2Numbers(SimpleMathOperations<T> sOperation, T a, T b)
    {
        return  sOperation.operation(a,b);
    }

    public T multiplication2Numbers(SimpleMathOperations<T> sOperation, T a, T b)
    {
        return sOperation.operation(a,b);
    }

    public T division2Numbers(SimpleMathOperations<T> sOperation, T a, T b)
    {
        return  sOperation.operation(a,b);
    }
    public T subtraction2Numbers(SimpleMathOperations<T> sOperation,T a, T b)
    {
        return  sOperation.operation(a,b);
    }

    public T sumNNumbers(SumOfNNumbers<T> sumOperation, List<T> localList)
    {
        return sumOperation.sum(localList);
    }

    public T sumNNumber2(Operation<T> operation, List<T> localList,T sum)
    {
        for(T i:localList)
        {
            sum = operation.execute(i,sum);
        }
        return sum;
    }

    public T add(Operation<T> operation,T a,T sum)
    {
        return  operation.execute(a,sum);
    }


}
