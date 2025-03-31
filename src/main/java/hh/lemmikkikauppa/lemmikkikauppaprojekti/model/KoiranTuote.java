package hh.lemmikkikauppa.lemmikkikauppaprojekti.model;

import jakarta.persistence.*;

@Entity
public class KoiranTuote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nimi;
    private String valmistaja;
    private int maaraVarastossa;

    private String tyyppi;
    private String vari;
    private String koko;
    private double hinta;

    // Getterit ja setterit...

    // id, nimi, valmistaja, maaraVarastossa
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }

    public String getValmistaja() { return valmistaja; }
    public void setValmistaja(String valmistaja) { this.valmistaja = valmistaja; }

    public int getMaaraVarastossa() { return maaraVarastossa; }
    public void setMaaraVarastossa(int maaraVarastossa) { this.maaraVarastossa = maaraVarastossa; }

    // Uudet kentät
    public String getTyyppi() { return tyyppi; }
    public void setTyyppi(String tyyppi) { this.tyyppi = tyyppi; }

    public String getVari() { return vari; }
    public void setVari(String vari) { this.vari = vari; }

    public String getKoko() { return koko; }
    public void setKoko(String koko) { this.koko = koko; }

    public double getHinta() { return hinta; }
    public void setHinta(double hinta) { this.hinta = hinta; }
}
