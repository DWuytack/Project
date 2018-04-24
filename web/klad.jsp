<!--
                    <div style="overflow-x: auto; pointer-events: all;">   
                        <table class="datatable">
                            <thead>
                                <tr>
                                    <th width="15%" onclick="sortTable(0)"><a>Achternaam</a</th>
                                    <th width="15%" onclick="sortTable(1)"><a>Voornaam</a</th>
                                    <th width="15%" onclick="sortTable(2)"><a>Login</a></th>
                                    <th width="15%" onclick="sortTable(3)"><a>Rol</a></th>
                                    <th width="15%" onclick="sortTable(4)"><a>GebtDatum</a></th>
                                    <th width="25%" onclick="sortTable(5)"><a>E-mail</a></th>
                                    <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                        <th class="actie">Acties</th>
                                    </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${lijstGebruikers}" var="cursist">
                                    <tr  <c:if test="${cursist.gebruikerID == sessionScope.editID}" >style="background-color: rgba(255,255,0,.5);"</c:if>>
                                        <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                            <td> <input type="text" name="achternaam"  value="${cursist.achternaam}"> </td>
                                            <td> <input type="text" name="voornaam" value="${cursist.voorNaam}"> </td>
                                            <td> <input type="text" name="login"  value="${cursist.login}"> </td>
                                            <td> 
                                    <select name="rol" value="${cursist.rol}">
                                        <option value="admin">admin</option>
                                        <option value="leerkracht">leerkracht</option>
                                        <option value="cursist">cursist</option>
                                        <option value="secretariaat">secretariaat</option></td>
                                    </select>
                                    <td> <input type="date" name="geboorteDatum" value="${cursist.geboorteDatum}"> </td>
                                    <td> <input type="text" name="email"  value="${cursist.email}"/> </td>
                                    </c:if>

                                <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                    <td> ${cursist.achternaam} </td>
                                    <td> ${cursist.voorNaam} </td>
                                    <td> ${cursist.login} </td>
                                    <td> ${cursist.rol} </td>
                                    <td> <fmt:formatDate value = "${cursist.geboorteDatum}" pattern="dd-MM-yyyy" /> </td>
                                    <td> ${cursist.email} </td>

                                </c:if>

                                <c:if test="${sessionScope.currentSessionUser.rol == 'admin'}" >
                                    <td class="actie">
                                        <c:if test="${cursist.gebruikerID != sessionScope.editID}" >
                                            <div class="actie-images">
                                                <span> <input type="image"  name="idEdit" value="${cursist.gebruikerID}" src='images/pencil.png'> </span>
                                                <span> <input type="image"  name="idDelete" value="${cursist.gebruikerID}" src='images/vuilbak.png'> </span>
                                            </div>
                                        </c:if>
                                        <c:if test="${cursist.gebruikerID == sessionScope.editID}" >
                                            <div class="actie-images">
                                                <span> <input type="image"  name="idSave" value="${cursist.gebruikerID}" src='images/green.png'> </span>
                                                <span> <input type="image"  name="idCancel" value="${cursist.gebruikerID}" src='images/cancel.png'> </span>
                                            </div>
                                        </c:if>
                                    </td>
                                </c:if>
                                </tr> 
                            </c:forEach>
                            </tbody>
                        </table>
                        -->