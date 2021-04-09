proc1 = ['/bin/bash', '-c', "/usr/bin/git ls-remote -h git@github.com:sumitbsn/jenkins-scripts.git"].execute()
proc2 = ['/bin/bash', '-c', "awk '{print \$2}'"].execute()
proc3 = ['/bin/bash', '-c', "sed s%^refs/heads%origin%"].execute()

all = proc1 | proc2 | proc3
String result = all.text

String filename = "/tmp/branches.txt"
boolean success = new File(filename).write(result) 

def multiline = "cat /tmp/branches.txt".execute().text
def branchdata = multiline.readLines()
print branchdata
def indexofbranch = branchdata.indexOf("bugfix/jenkins-test")

def temp = branchdata[0]
branchdata[0] = branchdata[indexofbranch]
branchdata[indexofbranch] = temp

def list = branchdata.readLines()
