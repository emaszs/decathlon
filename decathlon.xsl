<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
<head>
	<style>
		table {
			border-collapse: collapse;
		}
		table, th, td {
			border: 1px solid black;
		}
		th {
			width: 100px;
		}
		#ranking, #name, #total {
			font-weight: bold;
			text-align:center;
		}
		th#ranking, td#ranking {
			background-color: #ffdf80;
		}
		th#result, td#result {
			background-color: #cccccc;
			width: 50px;
		}
		th#total, td#total {
			background-color: #78caff;
		}
		th#name, td#name {
			background-color: #ffd24d;
		}
		body {
			font-family: "Segoe UI",Arial,sans-serif;
		}
	</style>
</head>
<body>
	<h2>Decathlon competition results</h2>
	<table border="">
		<tr>
			<th id="ranking">Ranking</th>
			<th id="name">Athlete</th>
			<th id="result">100m sprint</th>
			<th id="result">Long jump</th>
			<th id="result">Shot put</th>
			<th id="result">High jump</th>
			<th id="result">400m sprint</th>
			<th id="result">Hurdles</th>
			<th id="result">Discus throw</th>
			<th id="result">Pole jump</th>
			<th id="result">Javelin throw</th>
			<th id="result">1500m race</th>
			<th id="total">Total Score</th>
		</tr>
		<xsl:for-each select="decathlon/athlete">
		<tr>
			<td id="ranking"><xsl:value-of select="ranking"/></td>
			<td id="name"><xsl:value-of select="name"/></td>
			<td id="result"><xsl:value-of select="one_hundred"/></td>
			<td id="result"><xsl:value-of select="long_jump"/></td>
			<td id="result"><xsl:value-of select="shot_put"/></td>
			<td id="result"><xsl:value-of select="high_jump"/></td>
			<td id="result"><xsl:value-of select="four_hundred"/></td>
			<td id="result"><xsl:value-of select="hurdles"/></td>
			<td id="result"><xsl:value-of select="discus"/></td>
			<td id="result"><xsl:value-of select="pole"/></td>
			<td id="result"><xsl:value-of select="javelin"/></td>
			<td id="result"><xsl:value-of select="fifteen_hundred"/></td>
			<td id="total"><xsl:value-of select="total_score"/></td>
		</tr>
		</xsl:for-each>
	</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>