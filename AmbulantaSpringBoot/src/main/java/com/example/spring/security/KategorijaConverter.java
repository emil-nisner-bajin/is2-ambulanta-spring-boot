package com.example.spring.security;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import com.example.spring.repository.KategorijaRepository;

import model.Kategorija;

public class KategorijaConverter implements Converter<String,Kategorija> {
	
	KategorijaRepository kategorijaRep;
	
	public KategorijaConverter(KategorijaRepository k) {
		this.kategorijaRep = k;
	}

	@Override
	public Kategorija convert(String source) {
		int idKategorije = -1;
		try {
			idKategorije = Integer.parseInt(source);
		}catch(NumberFormatException e ) {
			throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Kategorija.class),idKategorije, null);
		}
		Kategorija k = kategorijaRep.getOne(idKategorije);
		return k;
	}
}
