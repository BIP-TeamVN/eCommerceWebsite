package com.hknp.controller.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/seller", "/admin/*"})
public class SellerFilter {
}
