package Backend;
import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Random;
import java.util.concurrent.Flow.Publisher;

import com.opencsv.CSVWriter;

public class create {
	static String[] type = { "Science", "Art", "Religion", "History", "Geography" };

	public static void book() {
		File file = new File("BOOK.CSV");
		FileWriter outputfile;
		try {
			outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);

			int flag = 0;
			for (int i = 1; i <= 1000000; i++) {
				flag++;
				int r = new Random().nextInt(81) + 20;

				int year = new Random().nextInt(71) + 1950;

				int category = new Random().nextInt(5);

				int copies = new Random().nextInt(21) + 15;

				int threshold = new Random().nextInt(10) + 5;
				// System.out.println("---"+threshold);
				String[] data1 = { Integer.toString(i), "Book " + Integer.toString(i),"Author "  + Integer.toString(flag),
						"Publisher " + Integer.toString(flag), Integer.toString(year), Integer.toString(r),
						type[category], Integer.toString(copies), Integer.toString(threshold) };
				if (i == 500000) {
					flag = 0;
				}
				writer.writeNext(data1);
				System.out.println(i);
			}

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void publisher() {
		File file = new File("PUBLISHER.CSV");
		FileWriter outputfile;
		try {
			outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);

			for (int i = 1; i <= 500000; i++) {

				Random generator = new Random();

				String strippedNum;
				int num1 = 0;
				int num2 = 0;
				int num3 = 0;

				num1 = generator.nextInt(600) + 100;// numbers can't include an 8 or 9, can't go below 100.
				num2 = generator.nextInt(641) + 100;// number has to be less than 742//can't go below 100.
				num3 = generator.nextInt(8999) + 1000; // make numbers 0 through 9 for each digit.//can't go below 1000.

				String[] data1 = { "Publisher " + Integer.toString(i), "Address " + Integer.toString(i),
						(Integer.toString(num1) + "-" + Integer.toString(num2) + "-" + Integer.toString(num3)) };

				writer.writeNext(data1);
				System.out.println(i);
			}

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		 publisher();
		book();
		

	}
}
