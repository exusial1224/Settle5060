package dao;

public class DynamicPricing {

	public int calculatePrice(int num, int init_price, int max_num) throws Exception {


		//num = いま何枚売れてるのか
		//init_price = 初期価格
		//max_num = 1タイムスロットの上限人数

        double priceMultiplier = 1.0;

        if (num <= max_num / 30) {
            priceMultiplier = 1.0;
        } else if (num <= max_num / 15) {
            priceMultiplier = 1.0625;
        } else if (num <= max_num / 10) {
            priceMultiplier = 1.375;
        } else if (num <= 2 * max_num / 15) {
            priceMultiplier = 1.4375;
        } else if (num <= max_num / 6) {
            priceMultiplier = 1.5;
        } else if (num <= max_num / 5) {
            priceMultiplier = 1.5625;
        } else if (num <= 3 * max_num / 10) {
            priceMultiplier = 1.625;
        } else if (num <= max_num / 3) {
            priceMultiplier = 1.75;
        } else if (num <= 11 * max_num / 30) {
            priceMultiplier = 1.875;
        } else if (num <= 2 * max_num / 5) {
            priceMultiplier = 2.0;
        } else if (num <= 13 * max_num / 30) {
            priceMultiplier = 2.125;
        } else if (num <= 7 * max_num / 15) {
            priceMultiplier = 2.25;
        } else if (num <= max_num / 2) {
            priceMultiplier = 2.375;
        } else if (num <= 8 * max_num / 15) {
            priceMultiplier = 2.5;
        } else if (num <= 17 * max_num / 30) {
            priceMultiplier = 2.75;
        } else if (num <= 3 * max_num / 5) {
            priceMultiplier = 3.0;
        } else if (num <= 19 * max_num / 30) {
            priceMultiplier = 3.25;
        } else if (num <= 2 * max_num / 3) {
            priceMultiplier = 3.5;
        } else if (num <= 7 * max_num / 10) {
            priceMultiplier = 4.0;
        } else if (num <= 11 * max_num / 15) {
            priceMultiplier = 4.5;
        } else if (num <= 23 * max_num / 30) {
            priceMultiplier = 5.0;
        } else if (num <= 4 * max_num / 5) {
            priceMultiplier = 5.5;
        } else if (num <= 5 * max_num / 6) {
            priceMultiplier = 6.0;
        } else if (num <= 13 * max_num / 15) {
            priceMultiplier = 6.75;
        } else if (num <= 9 * max_num / 10) {
            priceMultiplier = 7.5;
        } else if (num <= 29 * max_num / 30) {
            priceMultiplier = 10.0;
        } else if (num <= max_num) {
            priceMultiplier = 12.5;
        }

        // 最終価格の計算
        double last_price = init_price * priceMultiplier;

        //int型でreturn!
        return (int)last_price;
	}


    //価格の最終チェック関数
	public int decisionPrice(int calc_price, int update_price_counter, int low_price, int high_price) throws Exception {

		int dec_price = calc_price;

		if (update_price_counter < 0) {

			dec_price = calc_price - 50 * Math.abs(update_price_counter);

		}

		//最高価格以上なら最高価格に
		if (dec_price >= high_price) {

			dec_price = high_price;

		}

		//最低価格以下なら最低価格に
		if (dec_price <= low_price) {

			dec_price = low_price;

		}

		return dec_price;

	}

}
