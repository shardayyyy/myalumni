<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>





    <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td colspan="2" class="adminTableTrim"><html:img page="/images/spacer.gif" height="1" width="1"/></td>
                  </tr>
                  <tr class="bg0">
                    <td><div style="padding-bottom: 5px; padding-top: 5px;"> <html:img page="/images/spacer.gif" border="0" height="3" width="10"/> <span class="Bold"><bean:message key="label.reminisce.reminisce"/> </span> </div></td>
                    <td align="right">&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="2" class="adminTableBot"><html:img page="/images/spacer.gif" height="2" width="2"/></td>
                  </tr>
                  <tr>
                    <td colspan="2" >
                    
                    <fmt:bundle basename="ApplicationResources">
                        <display:table name="requestScope.listOfReminisce" cellpadding="3" cellspacing="1" pagesize="20" requestURI="/action/member/listReminisce?action=listReminisce" excludedParams="action" id="row">
                                <display:column titleKey="label.reminisce.when"  class="top">
                                    <c:out value="${row.slangYear}"/>
                                </display:column>
                                <display:column titleKey="label.reminisce.slang" class="top">
                                   <c:out value="${row.slang}"/>
                                </display:column>
                                <display:column titleKey="label.reminisce.pronounciation"  class="top">
                                	<c:out value="${row.pronounce}"/>
                                </display:column>                                
                                <display:column titleKey="label.reminisce.meaning"  class="top">
                                   <c:out value="${row.meaning}"/>
                                </display:column>
                        </display:table>  
                    </fmt:bundle>
                                    
                    </td>
                  </tr>
                </tbody>
              </table>
                </td>
            </tr>
          </table>


<%--

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" class="tborder">
  <tr>
    <td height="30" colspan="3" class="bg0">Reminisce</td>
  </tr>
  <tr class="portlet-section-body">
    <td width="16%"><div align="center"><strong>SLANG</strong></div></td>
    <td width="23%"><div align="center"><strong>PRONOUNCIATION</strong></div></td>
    <td width="61%"><div align="center"><strong>MEANING</strong></div></td>
  </tr>

    <tr class="portlet-section-body">
    <td>G4</td>
    <td>1998 </td>
    <td>Garri made from cassava</td>
  </tr>
  <tr class="portlet-section-body">
    <td>G4H2O</td>
    <td>1998 </td>
    <td>Sour garri water which is believe to relief one of tiredness</td>
  </tr>
  <tr class="portlet-section-body">
    <td>mu-bunk</td>
    <td>mu-bunk ( 92-97 ) </td>
    <td>When you have offended a senior, he then tells you to climb the bunk which is the bed and then he gives you the beaten of your life</td>
  </tr>
  <tr class="portlet-section-body">
    <td>lovers avenue</td>
    <td>LA (
1998 ) </td>
    <td>THE ROAD BETWEEN THE ENTRANCE OF THE NEWLY BUILT GIRL'S HOSTEL AND THE DINNING HALL, WERE LOVER MEET EVERY NIGTH</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Soak and Travel</td>
    <td>Soak and Travel  (
1988-2000 )</td>
    <td>Very little gari soaked in plenty of water (usually early in the morning before going for classes and left to rise to the brim till after classes in the afternoon). An act usually practised when all other provisions are exhausted and the quantity of gari left is already endangered</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Wudemi</td>
    <td>Wu-de-mi </td>
    <td>Soak and travel as in Garium Sulphate. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>cockroach</td>
    <td>cockroach...of course with a yoruba accent for effect<br>
(korkooroach)
( 79-86 ) </td>
    <td>to read at night with a torchlight...lantern and god knows what.....popular during exam and WAEC periods</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Tia u heading </td>
    <td>TEAR-YOU-HEADING  (1990-2001)</td>
    <td>Simply means that you are about to recieve a migrane alert. Some thug is about to smash your head using his own head(coconut in shape)</td>
  </tr>
  <tr class="portlet-section-body">
    <td>ojo yoo </td>
    <td>o-jo-yoooooooooooo   (1993-1997)</td>
    <td>vigilante`s(master`)are around!</td>
  </tr>
  <tr class="portlet-section-body">
    <td>mc jafextra </td>
    <td>mc ja-fe-stra  (1991-1996 )</td>
    <td>Students who struggle for reminants in the dinning hall</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Hot iron</td>
    <td>hot  ayooon<br>
1988-1993</td>
    <td>Term used by seniors to disperse the locker rooms when crowded at night especially after night prep. When you hear hot iron, you do not want to be burnt so you run for cover.</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Papo</td>
    <td>pap-po</td>
    <td>Someone who likes pap with passion sooo much that he will do anything for it....done with rushing attitude especially those days there was no one controlling the dining hall meal periods</td>
  </tr>
  <tr class="portlet-section-body">
    <td> Lole   </td>
    <td>LoLay </td>
    <td>Nickname for the master in charge of the boys. Notorious for his expertise with the cane! </td>
  </tr>
  <tr class="portlet-section-body">
    <td>moi square  </td>
    <td>moi-square </td>
    <td>bean cakes sold in the admin building, at least while i was there. the process of procuring one was extremely risky as one had to get slip through the principal's office without been caught</td>
  </tr>
  <tr class="portlet-section-body">
    <td>luxi don land</td>
    <td>luxi-don-land </td>
    <td>the luxurious buses that took students home on the last day of school had arrived. after a hard semester, students pant for and herald these buses. not all student's rode however, mostly lower class student's who lived in Lagos </td>
  </tr>
  <tr class="portlet-section-body">
    <td>bang &amp; blast</td>
    <td>bang &amp; blast </td>
    <td>do poorly/excellently on a test respectively </td>
  </tr>
  <tr class="portlet-section-body">
    <td>concusion</td>
    <td>con-co-tion </td>
    <td>mixing all your provisions when in dire need </td>
  </tr>
  <tr class="portlet-section-body">
    <td>la base</td>
    <td>la-base </td>
    <td>Let me have a spoon of your garium sulphate or whatever you have </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Stab</td>
    <td>stab </td>
    <td>Refusing to attend school activities as planned in the timetable. (e.g. I am going to stab prep) </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Wudemi</td>
    <td>Wu-de-mi </td>
    <td>Soak and travel as in Garium Sulphate. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>breastin akele</td>
    <td>BREASTIN AKELE </td>
    <td>Girls with big large chest. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Galuwa  </td>
    <td>Ga-lu-wa </td>
    <td>This a manner of jogging peculiar to boys especially those in the football team when training on the pitch or when going for road-walk (slow jogging).<br>
                    <br>
when galuwa-ing, you jog with your legs at odd angles to your body (as if a bowling ball was hanging between your legs), while raising your knees up to about 90-degrees.<br>
                    <br>
Galuwa is normally accompanied with a song that goes thus:<br>
<br>
&quot;Alhaji Bello Epon mi re galuwa galuwa&quot; (repeat till you get bored)</td>
  </tr>
  <tr class="portlet-section-body">
    <td>One Boy</td>
    <td>one booooy!!</td>
    <td>A general call-out, by a senior student, to all junior students. The last student to get to the senior usually has to do whatever the senior student wants eg wash his or her clothes. Junior students are severely punished if they fail to respond to a &quot;one boy&quot; call. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Angola </td>
    <td>An-Go-La</td>
    <td>the place behind yellow house.....86-89 (webmaster comments: you know what you guys go there for !!! to squat;)...... I dey go Angolaaa ! )</td>
  </tr>
  <tr class="portlet-section-body">
    <td>posh</td>
    <td>posh </td>
    <td>Very watery beans. Cooked in the Kitchen near the dining Hall. Was the student's choice then...wonder if it is still their choice!! </td>
  </tr>
  <tr class="portlet-section-body">
    <td>soka </td>
    <td>sh-oka </td>
    <td>To expose a student who has committed an offence against the school authority. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Okpata</td>
    <td>o-pa-ya </td>
    <td>When someone/somebody talks rubbish (ie talking without making sense) </td>
  </tr>
  <tr class="portlet-section-body">
    <td>P.M.C</td>
    <td>Poor Mans Conner </td>
    <td>Desolate room close to the mini-mart/talk shop where juniors hid from ruthless seniors that tried to take their money or their loaves. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>koba </td>
    <td>koba </td>
    <td>To intentionally betray another student by reporting him or her to  a teacher. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Roks </td>
    <td>Rrucks </td>
    <td>an intended joke that is not at all funny or amusing to the audience. e.g everything Mr. West said in class.</td>
  </tr>
  <tr class="portlet-section-body">
    <td>o gbe yo</td>
    <td>oh gbe your </td>
    <td>masters are in the hostels please vacate the area fast, by any available route to the safest point ...... angola. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>joge</td>
    <td>jo-ghe </td>
    <td>hot tea </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Fashi</td>
    <td>Fah-shee </td>
    <td>1) A hip way to say &quot;forget about it.&quot; For example, to fashi a test means to avoid taking the test.<br>
2) Fashi is also used to address something or someone good looking, successful or outstanding. For example, &quot;Fashi that fine girl man&quot; is a saying that is commonly used by guys admiring a pretty lady. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Kunzi</td>
    <td>Koon-zee </td>
    <td>Kuli-kuli: A cruncy snack made of groundnut. Students usually &quot;soak&quot; (eat) it with garri. </td>
  </tr>
  <tr class="portlet-section-body">
    <td>Ibun</td>
    <td>E-bun</td>
    <td>Improper use of the English Language</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Killer Base</td>
    <td>KILLA-BASE</td>
    <td> A term used to describe an over- heaped
spoon of food, which normally occurs when
2 or more students are eating together<br></td>
  </tr>
  <tr class="portlet-section-body">
    <td>Bondon </td>
    <td>BON-DON</td>
    <td>A very comfortable position of sitting on
the bathroom wall or on a window seal and dumping.</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Be ina </td>
    <td>BE-NO</td>
    <td>A term used to describe smoking</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Palmi </td>
    <td>PAMI</td>
    <td>&quot;Palmwine&quot; smuggled into the hostel.</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Shak</td>
    <td>SHAQ</td>
    <td>A term used to describe the  drinking of alcohol</td>
  </tr>
  <tr class="portlet-section-body">
    <td>meskayan<br></td>
    <td>mes-ka-yan ( 1994-2000 ) </td>
    <td>you siad the wrong thing or you went off piont, or you gossip too much</td>
  </tr>
  <tr class="portlet-section-body">
    <td>Based on pin code</td>
    <td>Based on pin code (2000)</td>
    <td>Mutual Secret</td>
  </tr>
  <tr class="portlet-section-body">
    <td>CAMA YAN </td>
    <td>(2000)</td>
    <td>DISCUSSING/CONVERSING NEGATIVELY
</td>
  </tr>
  <tr class="portlet-section-body">
    <td>PEPPER RESTED </td>
    <td>(2000)</td>
    <td>ENOUGH CASH</td>
  </tr>
  <tr class="portlet-section-body">
    <td>TREATING OF FUCKUPS</td>
    <td>(2000)</td>
    <td>DISCLIPINE</td>
  </tr>
  <tr class="portlet-section-body">
    <td>STREET IS TIGHT </td>
    <td>(2000)</td>
    <td>VIGILANTE EVERYWHERE</td>
  </tr>
  <tr class="portlet-section-body">
    <td>DISCHARGE </td>
    <td>(2000)</td>
    <td>RUNNING AWAY</td>
  </tr>
  <tr class="portlet-section-body">
    <td>BOLOSCONE</td>
    <td>(2000)</td>
    <td>NO INSPECTION FOR THE ELITES</td>
  </tr>
  <tr class="portlet-section-body">
    <td colspan="3" align="center" class="redhighlight">Email your reminiscing moments to <bean:message key="email.webmaster"/>, and make the subject "FGC - Reminisce"...</td>

  </tr>
</table>

--%>
