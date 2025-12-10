<h2>METRO2 file format Parser and Validator</h2>
MetroParser is a simple Maven-based Java project intended for parsing METRO2 data.
The project uses Java 19, is built using Maven, and includes basic unit tests with JUnit 5.

<ul>
  <li>Enum-determined fields names and position data</li>
  <li>Validator for dates and telephone number (regex analysis and date consistency</li>
  <li>ErrorLog provisioning</li>
  <li>Reporting critical issues</li>
  <li>Buffered reading in chunks of 128 kB</li>
  <li>Thread-safe buffered reader</li>
</ul>

📋 Requirements:</br>
<li>Java JDK 19</li>
<li>Apache Maven 3.8+</li>
</br>
Check your versions:</br>
<i>java -version</i></br>
<i>mvn -version</i>

🔧 Build the project</br>
<i>mvn clean package</i>

This command:
<li>removes previous build artifacts (clean)</li>
<li>compiles the source code (configured for Java 16 via the maven-compiler-plugin)</li>
<li>runs the tests</li>
<li>produces a JAR file at target/MetroParser-1.0.jar</li>
</br>
📥 Install to local Maven repository</br>
<i>mvn clean install</i>

🧪 Run tests</br>
<i>mvn test</i>

▶️ Run the Application

If your JAR contains a Main class defined in the MANIFEST.MF:</br>
<i>java -jar target/MetroParser-1.0.jar</i>
