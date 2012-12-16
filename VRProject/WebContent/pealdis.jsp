<script lang="text/javascript">
function kinnita() {
    var vastus = confirm("Kinniti andmebaasi algse seisu taastamine?")
    if (vastus) window.location = "DatabaseDAO";
}
</script>
<table width="880px">
	<tr>
		<td align="left">
			<ul>
				<li>
					<font>
						<i>
							<a href='#' onclick='kinnita()'>
								Taasta andmebaasi algne seis!
							</a>
						</i>
					</font>
					<%if (request.getParameter("loodud") != null) {
			       		out.println("<font style='color:green; font-weight: bold'>(Uuesti) loodud!</font>");
					}%>
				</li>
				<li><a href='VaataAdminAlluvusi'>Vaata admin alluvusi</a></li>
				<li><a href='VaataRiigiAdminYksusi'>Vaata riigi admin üksuseid</a></li>
				<li><a href='VaataRiigiAdminYksuseLiike'>Vaata riigi admin üksuse liike</a></li>
				<li><a href='VaataVoimalikeAlluvusi'>Vaata võimalike alluvusi</a></li>
				<li><b>Realiseeritud vaated: <a href='V1'>V1</a> | <a href='V2'>V2</a> 
				| <a href='V3'>V3</a> | <a href='V4'>V4</a> | <a href='./'>V5</a>
				</b></li>
			</ul>
		</td>
		<td align="right">
			<a href='./' title='mine avalehele'><img src='pildid/ITK.png' align='right'></a>
		</td>
	</tr>
</table>