<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/15/2015
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="avatarEditModal" tabindex="-1" role="dialog" aria-labelledby="avatarEditModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="upload_avatar" method="post" target="hiddenAvatarFrame" enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close avatarEditModal-close-modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="avatarEditModalLabel">Avatar</h4>
                    <input type="hidden" id="avatarEditModal_id">
                </div>
                <div class="modal-body">
                    <div class="form-horizontal" role="form">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label class="" for="avatarEditModal_file">File</label>
                                    <input type="hidden" id="contactIdForAvatar" name="contact_id" />
                                    <%--<input type="file"  class="form-control"  id="avatarEditModal_file">--%>

                                    <input type="file" id="avatarEditModal_file" name="avatar" />
                                    <%--<input type="submit" name="upload" id="upload" value="Загрузить" />--%>

                                    <div id="res"></div>
                                </div>
                            </div>
                        </div>

                        <div class="alert alert-danger" role="alert" id="avatarEditModalAlert" style="display:none;">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default avatarEditModal-close-modal">Close</button>
                    <input type="submit" class="btn btn-primary" id="avatarEditModalApply" value="Apply">
                </div>
            </form>
        </div>
    </div>
</div>
