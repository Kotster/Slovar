package com.example.res;

import java.util.ListResourceBundle;

public class resource_ru_RU extends ListResourceBundle{
	private static final Object[][] contents ={
		{"Menu", "1:Выбрать словарь \r\n 2:Вывести словарь \r\n 3:Добавить значение в словарь \r\n 4:Удалить значение из словаря \r\n 5:Найти значение по ключу"},
		{"Slov1", "Введите номер словаря:"},
		{"SelectSlov", "Выбери словарь"},
		{"WriteKey","Введите Ключ:"},
		{"WriteValue","Введите Значение:"},
		{"NoIndexSlovArr","Словаря с таким индексом нет"},
		{"ErrorKey","Введен не валидный ключ"},
		{"NotUniqKey","Введен не уникальный ключ"}
	};
	public Object[][] getContents(){return contents;}
}