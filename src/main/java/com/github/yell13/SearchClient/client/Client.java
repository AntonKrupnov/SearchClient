package com.github.yell13.SearchClient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Anton 15.05.2018
 */
@FeignClient(name = "client")
public interface Client {


    @RequestMapping(method = RequestMethod.PUT, path = "/documents/{key}")
    void storeDocument(@PathVariable("key") String key, @RequestBody String document);

    @RequestMapping(method = RequestMethod.GET, path = "/documents/{key}")
    String getDocument(@PathVariable("key") String key);

    @RequestMapping(method = RequestMethod.GET, path = "/keys")
    String findKeysWithTokens(@RequestParam(value = "tokens") String tokens);
}
