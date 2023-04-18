package pojos;

public class PojoHerokuappBookingdates {
/*
Request body
{
"firstname" : "Ahmet",
"lastname" : “Bulut",
#"totalprice" : 500,
"depositpaid" : false,
"bookingdates" : {
"checkin" : "2021-06-01",
"checkout" : "2021-06-10"
"additionalneeds" : "wi-fi" }
 */
//1) Tum variable’lari "private" olarak olusturalim
    private String checkin;
    private String checkout;
//2) Tum variable’lar icin getter( ) and setter( ) metodlari olusturalim

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

//3) Tum parametreleri iceren bir constructor olusturalim

    public PojoHerokuappBookingdates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

// 4) Default constructor (parametresiz) olusturalim

    public PojoHerokuappBookingdates() {
    }

//5) toString( ) metodu olusturalim


    @Override
    public String toString() {
        return "PojoHerokuappBookingdates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';

    }
}
