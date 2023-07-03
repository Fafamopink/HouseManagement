package model;

public class HOUSESDATA {
	
	    protected int housenum;
	    protected String housename;



	    public HOUSESDATA (int housenum, String housename) {

	        this.housenum = housenum;
	        this.housename = housename;


	    }



		public String getHousename() {
			return housename;
		}

		public void setHousename(String housename) {
			this.housename = housename;
		}

		public int getHousenum() {
			return housenum;
		}

		public void setHousenum(int housenum) {
			this.housenum = housenum;
		}



	
}
