package com.example.calculator;

public class Calculate {
    private static final String digits = "0123456789ABCDEF";
    public static String Into(Integer FirstSys, Integer SecondSys, String  Number) {
        double val = 0;
        int i = 0;
        boolean positive = true;
        if (Number.charAt(0)=='-'){
            positive=false;
            Number=Number.substring(1);
        }
        while(i < Number.length()) // пока не кончилась строка
        {
            char c = Number.charAt(i);
            if(c == '.'||c==',') { // нашли точку
                i++; // Переместить на следующий символ и выйти из цикла.
                break;
            }
            int d = digits.indexOf(c); // Индексы совпадают с числами из [0..15]
            if(d == -1 || d >= FirstSys)
                return "Ошибка символа";
            val = FirstSys * val + d;
            i++;
        }

        int power = 1; // вычислить лишний порядок.
        while(i < Number.length())
        {
            char c = Number.charAt(i);
            int d = digits.indexOf(c);
            if(d == -1 || d >= FirstSys)
                return "Ошибка символа";
            power *= FirstSys; // увеличиваем степень порядка на единицу
            val = FirstSys * val + d;
            i++;
        }
        double result=val/power; //значние в десятичной системе
        //перевод в систему помимо десятичной
        String newNumber= "";
        if (SecondSys == 10){
            if (!positive){
                newNumber+='-';
            }
            newNumber+=String.valueOf(result);
        }
        else {
            int integerPart = (int) result; //целая часть
            double fractionalPart = result-integerPart; //дробная часть
            while (integerPart/SecondSys!=0){
                newNumber = newNumber + digits.charAt(integerPart % SecondSys);
                integerPart/=SecondSys;
            }
            newNumber = newNumber + digits.charAt(integerPart % SecondSys);
            if (!positive){newNumber+='-';} //проверка отрицательности
            newNumber = new StringBuffer(newNumber).reverse().toString();

            int powerMax=7;  //максимальный порядк дробной части
            if (fractionalPart>0){
                newNumber+='.';
            }
            while (powerMax>0 && fractionalPart!=0){
                fractionalPart*=SecondSys;
                newNumber = newNumber + digits.charAt((int)fractionalPart);
                fractionalPart-=(int)fractionalPart;
                powerMax-=1;
            }
        }
        return newNumber;
    }
}
