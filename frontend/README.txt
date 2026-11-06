ROSIDHA K. — JAVA FREELANCER PORTFOLIO (VERSION 3)
===================================================

ABOUT
-----
A modern light-theme desktop portfolio built entirely with Java Swing and AWT.
It runs as a desktop application; it is not an HTML website.

FEATURES
--------
- Sidebar navigation with Home, About, Skills, Services, Projects and Contact.
- Colourful light interface with a deep-purple sidebar, blue/teal/coral accents, pastel hero panel and coloured card-top highlights.
- Overview hero section and profile snapshot.
- About/profile details.
- Skill progress bars (sample levels; edit them to reflect your real skills).
- Service and project cards.
- Contact form with required-field and email validation.
- Contact messages saved locally in contact-messages.txt.
- Scrollable pages and resizable application window.

REQUIREMENTS
------------
- JDK 17 or later (JDK 21 is suitable).
- VS Code (or another Java IDE).

RUN IN VS CODE
--------------
1. Extract the ZIP.
2. In VS Code choose File > Open Folder.
3. Open the inner folder named rosidha-java-portfolio-v3.
   It must contain README.txt, PROJECT-REPORT.txt and the src folder.
4. Choose Terminal > New Terminal.
5. Compile:
      javac -d out src\PortfolioApp.java
6. Run:
      java -cp out PortfolioApp

CUSTOMIZE BEFORE SUBMISSION
---------------------------
Open src/PortfolioApp.java and update:
- Add your real email address in the Contact Information section.
- Update your actual skill levels and descriptions.
- Replace sample project descriptions with your completed work.
- Add your GitHub and LinkedIn links if you want them displayed.
- Keep project descriptions truthful; label unfinished work as a concept or in progress.

CONTACT FORM
------------
The form validates all fields and basic email format. It appends messages to
contact-messages.txt in the folder from which the program is run. It does not
send email or upload messages to a server.

TECHNOLOGIES
------------
Java 17+, Java Swing, Java AWT, Java NIO.
No HTML, CSS, JavaScript, Maven, external libraries or paid APIs are required.
