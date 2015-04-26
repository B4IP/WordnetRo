import urllib2

data = {'sl':'en','tl':'it','text':'word'} 
request = urllib2.Request('https://translate.google.com/translate_a/single?client=t&sl=ro&tl=en&hl=en&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&source=btn&ssel=0&tsel=0&kc=27&tk=519674|233426&q=Azi%20merg%20la%20facultate.')

request.add_header('User-Agent', 'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11')
opener = urllib2.build_opener()
feeddata = opener.open(request).read()
print feeddata
