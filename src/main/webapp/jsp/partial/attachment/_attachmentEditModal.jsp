<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/14/2015
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="attachmentEditModal" tabindex="-1" role="dialog" aria-labelledby="attachmentEditModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close attachmentEditModal-close-modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="attachmentEditModalLabel">Attachment of</h4>
        <input type="hidden" id="attachmentEditModal_id">
      </div>
      <div class="modal-body">
        <div class="form-horizontal" role="form">

          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <label class="" for="attachmentEditModal_filename">File name</label>
                <input type="text"  class="form-control small-padding" id="attachmentEditModal_filename">
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <label class="sr-only" for="attachmentEditModal_comment">Comment</label>
                <input type="text" class="form-control" id="attachmentEditModal_comment">
              </div>
            </div>
          </div>

          <div class="alert alert-danger" role="alert" id="attachmentEditModalAlert" style="display:none;">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default attachmentEditModal-close-modal">Close</button>
        <button type="button" class="btn btn-primary" id="attachmentEditModalApply">Apply</button>
      </div>
    </div>
  </div>
</div>
