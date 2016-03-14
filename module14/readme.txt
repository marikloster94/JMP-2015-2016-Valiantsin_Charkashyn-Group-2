1. Download and install jenkins: 
	- from (https://jenkins-ci.org/) Jenkins Java Web Archive (.war) and unpack it (java -jar jenkins.war) 
	- form (http://ftp.icm.edu.pl/packages/jenkins/windows/jenkins-1.652.zip) and install Jenkins as a Windows service
	After install you can go to jenkins main page: http://localhost:8080

2. Install JDK7, Maven, Ant
	Download jdk7 from official site (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html ). Set system environment varialibles (JAVA_HOME, PATH, JRE_HOME).
	Download ant from ( https://ant.apache.org/bindownload.cgi ) and unpack it. Set system environment varialibles (ANT_HOME, PATH).
	Download maven from ( http://maven.apache.org/download.cgi ). Set system environment varialibles ().

3. Configure Jenkins with installed JDK, Maven, Ant:
	Go to http://localhost:8080/configure and configure Jenkins with installed JDK, Maven, Ant. 
	Install ant: Find item ANT on page ( http://localhost:8080/configure ) and fill to fields: name and ANT_HOME (path to ant folder).

4. Configure Jenkins with additional JDK (auto-installed)
	Go to http://localhost:8080/configure to item JDK and add new JDK (jdk1.7.0_80).
	For adding auto-installed JDK set checkbox "Install automatically", choose version of jdk, accept license agreement 
	and enter your Oracle credentials.
	
5.  Install 10 Jenkins plugins
	Go to http://localhost:8080/pluginManager/available and choose necessary plugins for install. 
	For example, check checkbox near next plugins (Git plugin, Mailer Plugin, Git client plugin,
	Parametrized trigger, email-ext, Scriptler, Groovy postbuild, Locale plugin) and click button "Install without restart".
	
	You can see all already installed plugins on the page http://localhost:8080/pluginManager/installed .
	
6.  Configure Jenkins security (install Role strategy plugin). Remove anonymous access. Create administrator user (all permissions) 
and developer user (build job, cancel builds).

	Download Role strategy plugin from https://wiki.jenkins-ci.org/display/JENKINS/Role+Strategy+Plugin . Go to page http://localhost:8080/pluginManager/advanced
	and find item load plugin.
	Activate the Role-Based Strategy by using the standard Manage Jenkins > Configure System (http://localhost:8080/configureSecurity) .
	On this page choose checkbox "Enable security", then set "TCP port for JNLP slave agents" to Random, in "Access control" item set value for "Security realm" to "Delegate to servlet container" 
	and for "Authorization" to "Role-Based Strategy". Save chnges.
	Define and assign roles by using the Manage and Assign Roles item which appears in the Manage Jenkins (http://localhost:8080/role-strategy/). Find item Global roles, enter in field "Role to add" name of new role (developer)
	and click add. Choose in column "Job" two functions: build and cancel. Save changes by clicking save button at the end of page.
	
7.  Create simple free-style job
	On the main page (http://localhost:8080/) click link "Create new job". Enter name of your jab to field "Item name" and choose "Create simple free-style job" then click Ok.
	On the next page insert all requied data that applies to your project (i.e. project description, jdk, manage project versions, triggers, build action, what should do after build and other)

8.  Setup simple trigger and build action (echo current build number)	
	
	Setup trigger:
	- Choose job Find item "Build triggers". Choose subitem "Build periodically", at the timetable field enter cron 
	(i.e. 30 9 * * * for running build every day at 9:30 am) to determine start build. 
	
	Set build action:
	- Find item "Build", choose from dropdown "Invoke top-level Maven targets", then enter maven version 
	( it was configured in item number 2), maven goals ( i.e. package) and set path to the pom file (i.e. module11/parent/MavenTask/pom.xml). Then click Save.
	
9.  Send email about build result

	Find item "Post build action" and enter all email recievers, who should know building result. Click button Save.
	
	