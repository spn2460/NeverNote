/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outbottle.controllers;

import models.Notebook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
/**
 *
 * @author john
 */
@Controller
public class DefaultController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
       map.put("msg", "Hello Spring 4 Web MVC!");
       return "index";
    }
   
   // create notebook
   @RequestMapping(value = "notebooks", method = RequestMethod.POST)
   public String createNotebook(ModelMap map) {
       map.put("msg", "Notebook Created");
       return "index";
   }
   // delete notebook
   @RequestMapping(value = "/notebooks/delete", method = RequestMethod.POST)
   public ResponseEntity<Notebook> deleteNotebook(@RequestBody Notebook notebook) {
       if (null != notebook){
           notebook.title += "hello";
       }
       return new ResponseEntity<>(notebook, HttpStatus.OK);
   }
   
   // create note
      @RequestMapping(value = "notes", method = RequestMethod.POST)
   public String createNote(ModelMap map) {
       map.put("msg", "Note Created");
       return "index";
   }
   
   // retrieve notes
   @RequestMapping(value = "notes/tag/{tag}", method = RequestMethod.GET)
   public String getNoteByTag(ModelMap map) {
       map.put("msg", "Note retrieved");
       return "index";
   }
   
      // retrieve notes
   @RequestMapping(value = "notes/title/{title}", method = RequestMethod.GET)
   public String getNoteByTitle(ModelMap map) {
       map.put("msg", "Note retrieved");
       return "index";
   }
   
      // retrieve notes
         @RequestMapping(value = "notes/notebook/{ID}", method = RequestMethod.GET)
   public String getAllNotes(ModelMap map) {
       map.put("msg", "All Notes retrieved");
       return "index";
   }
   
   // update note , by id
            @RequestMapping(value = "notes/edit/{title}/{body}", method = RequestMethod.PUT)
   public String editBody(ModelMap map) {
       map.put("msg", "Body Updated");
       return "index";
   }
   
   // delete note ,by title
        @RequestMapping(value = "notes/id", method = RequestMethod.DELETE)
   public String deleteNote(ModelMap map) {
       map.put("msg", "Note deleted");
       return "index";
   } 
}
