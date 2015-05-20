/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import translate.apis.Dictio;
import translate.apis.Translation;

/**
 *
 * @author Vlad
 */
public class DictioTest {
    
    public static void main(String[] args) {
        Dictio dictio = new Dictio("en", "ro");
        Translation tr = dictio.getCandidates("masterpiece");
        System.out.println(tr);
    }
    
}
