package com.example.quran_app;

public class SurahData {

    String surahNameUrdu;
    String surahNameEnglish;
    String id;
    String Nazool;

    SurahData(String id, String surahNameUrdu, String surahNameEnglish, String Nazool)
    {
        this.surahNameUrdu = surahNameUrdu;
        this.surahNameEnglish = surahNameEnglish;
        this.id = id;
        this.Nazool = Nazool;
    }

    SurahData(String surahNameUrdu, String surahNameEnglish)
    {
      this.id = "";
      this.Nazool = "";
      this.surahNameUrdu = surahNameUrdu;
      this.surahNameEnglish = surahNameEnglish;
    }


}
