package br.com.learning.junit5.utils;


public enum Doctor {

   avery("Ralph Avery"),
   johnson("Beth Johnson"),
   murphy("Pat Murpy");

   private String name;

   Doctor(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
