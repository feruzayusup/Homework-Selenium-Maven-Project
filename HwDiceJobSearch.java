package com.dice;
	
		import java.io.BufferedReader;
		import java.io.FileNotFoundException;
		import java.io.FileReader;
		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.List;
		import java.util.Random;
		import java.util.concurrent.TimeUnit;

		import org.openqa.selenium.By;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.chrome.ChromeDriver;

		import io.github.bonigarcia.wdm.WebDriverManager;

		public class HwDiceJobSearch {

			public static void main(String[] args) throws IOException, InterruptedException {

				WebDriverManager.chromedriver().setup();
				WebDriver driver = new ChromeDriver();
				driver.manage().window().fullscreen();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String url = "https://www.dice.com/";
				
				driver.get(url);
				String actualTitle = driver.getTitle();
				String expected = "Job Search for Technology Professionals | Dice.com";
				if (actualTitle.equals(expected)) {
					System.out.println("Pass");
				} else {
					System.out.println("Fail");
				}
				List<String> jobs = new ArrayList<>();
				FileReader fr = new FileReader("Jobs.txt");
				BufferedReader br = new BufferedReader(fr);
				String jobTitle = "";
				while ((jobTitle = br.readLine()) != null) {
					jobs.add(jobTitle);
				}
				// Random rd = new Random();
				driver.findElement(By.id("search-field-keyword")).clear();
				String job = "";
				List<String> total = new ArrayList<>();
				for (String string : jobs) {
					driver.findElement(By.id("search-field-keyword")).clear();
					driver.findElement(By.id("search-field-keyword")).sendKeys(string);
					String zip = "77064";
					driver.findElement(By.id("search-field-location")).clear();
					driver.findElement(By.id("search-field-location")).sendKeys(zip);
					driver.findElement(By.id("findTechJobs")).click();
					String str = driver.findElement(By.id("posiCountId")).getText();
					int count = Integer.parseInt(str.replace(",", ""));
					total.add(string + ": " + count);
					driver.navigate().back();
				}
				for (String string : total) {
					System.out.println(string);
				}

				driver.close();
			}
		
		
	}


