<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Æ:Diary</title>
</head>
<body>

<h1>Æ:Diary</h1>

<p><strong>Æ:Diary</strong> is an Android application designed specifically to assist class monitors in tracking student attendance and analyzing attendance data. This app simplifies attendance management by providing an intuitive interface, data storage, and various statistics and analytics features.</p>

<h2>📝 About the App</h2>

<p>Æ:Diary aims to streamline the process of managing student attendance. It offers essential features such as recording daily attendance, viewing overall attendance statistics, managing student schedules, and customizing the app experience with language and theme settings. This app is particularly useful for class monitors or teachers who need a reliable and straightforward tool for attendance tracking.</p>

<h2>📱 Screenshots</h2>

<div align="center">
    <img src="https://github.com/de4ltt/AE.Diary/blob/master/ae-diary.jpg" width="800" alt="Light and Dark Theme UI">
</div>

<h2>⚙️ Technology Stack</h2>

<p>The app is built using a range of modern Android technologies to ensure efficiency, maintainability, and a smooth user experience:</p>

<ul>
    <li><strong>Kotlin</strong> – Used as the primary programming language for its conciseness, safety, and interoperability with Android APIs.</li>
    <li><strong>Jetpack Compose</strong> – The UI toolkit that allows developers to build responsive and intuitive user interfaces in a declarative manner.</li>
    <li><strong>Room</strong> – Provides a robust, SQLite-based local database solution for storing attendance data and other relevant information.</li>
    <li><strong>Kotlin Coroutines</strong> and <strong>Flow</strong> – For managing asynchronous operations and reactive data streams within the app.</li>
    <li><strong>Hilt</strong> – Used for dependency injection, helping to manage and inject dependencies efficiently across the app.</li>
</ul>

<h2>🚀 Getting Started</h2>

<p>Follow these steps to set up and run the app locally:</p>

<h3>Installation</h3>

<ol>
    <li>Clone the repository from GitHub:
        <pre><code>git clone https://github.com/de4ltt/AE.Diary.git</code></pre>
    </li>
    <li>Open the project in <strong>Android Studio</strong>.</li>
    <li>Build the project and ensure all dependencies are installed.</li>
</ol>

<h3>First Launch</h3>

<ol>
    <li>On the first launch of the app, navigate to the <strong>Settings</strong> menu.</li>
    <li>Set the start and end dates of the semester to enable accurate tracking of attendance within the specified timeframe.</li>
</ol>

<h2>🔑 Key Features</h2>

<ul>
    <li><strong>Attendance Tracking:</strong> Record and monitor student attendance for each class session. Mark students as present or absent to keep an accurate attendance log.</li>
    <li><strong>Attendance Statistics:</strong> View detailed attendance statistics for each student or subject, helping to identify attendance patterns and analyze overall attendance trends.</li>
    <li><strong>Timetable Management:</strong> Create and manage class schedules, including adding, editing, and organizing subjects and weekly sessions.</li>
    <li><strong>Language Support:</strong> Choose from multiple languages to customize the app experience for different users.</li>
    <li><strong>Light and Dark Themes:</strong> Switch between light and dark themes to match user preferences and improve usability in different lighting conditions.</li>
</ul>

<h2>📂 Code Examples</h2>

<p>Below are some examples of the core components used in the app, demonstrating dependency injection with Hilt and database operations with Room.</p>

<h3>Example of Using Hilt for Dependency Injection</h3>

<p>The following module uses Hilt to bind an interface to its implementation, allowing for flexible and reusable code:</p>

<pre><code>@Module
@InstallIn(SingletonComponent::class)
abstract class MainScreenModule {

    @Binds
    @Singleton
    abstract fun bindMainScreenUseCases(
        mainScreenUseCasesImpl: MainScreenUseCasesImpl
    ): MainScreenUseCases

}
</code></pre>

<h3>Example of Using Room DAO for Managing Student Data</h3>

<p>This DAO (Data Access Object) interface defines methods to perform operations on the students table, including fetching, upserting, and deleting student records. Using Flow enables reactive, asynchronous data streams:</p>

<pre><code>@Dao
interface StudentsDAO {

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow&lt;List&lt;Students&gt;&gt;

    @Upsert
    fun upsertStudent(student: Students)

    @Delete
    fun deleteStudent(student: Students)

}
</code></pre>

<h2>🎨 Themes</h2>

<p>The app includes support for both light and dark themes, allowing users to choose the display mode that best suits their environment. The UI adapts automatically to the chosen theme, providing a seamless and visually appealing experience in both modes.</p>

<h2>📌 Additional Information</h2>

<p><strong>Æ:Diary</strong> is a non-commercial project developed for educational purposes and personal use. The app serves as a demonstration of modern Android development practices, showcasing the effective use of Jetpack libraries, dependency injection, asynchronous programming, and more.</p>

<h2>📥 Download</h2>

<p>The latest version of Æ:Diary can be downloaded from the <a href="https://github.com/de4ltt/AE.Diary/releases">Releases</a> section on GitHub. Each release includes an APK file ready to be installed on Android devices.</p>

<h2>💬 Contact</h2>

<p>If you have any questions, feedback, or suggestions, feel free to open an issue or discussion in the repository. Contributions to the project are welcome, especially suggestions for new features or improvements.</p>

</body>
</html>
