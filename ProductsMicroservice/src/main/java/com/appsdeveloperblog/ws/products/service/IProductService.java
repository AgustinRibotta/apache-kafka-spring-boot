package com.appsdeveloperblog.ws.products.service;

import com.appsdeveloperblog.ws.products.rest.CreateProductRestModel;

public interface IProductService {
    
    String createProduct(CreateProductRestModel productRestModel);
    
}
