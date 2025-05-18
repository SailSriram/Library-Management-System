package com.library.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.management.domain.Checkout;

@Service
public class CheckoutService 
{
	private List<Checkout> checkouts;
	
	public List<Checkout> getCheckOuts()
	{
		return checkouts;
	}

}
