<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 12/15/2015
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="attachmentAddModal" tabindex="-1" role="dialog" aria-labelledby="attachmentAddModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close attachmentAddModal-close-modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="attachmentAddModalLabel">Attachment of</h4>
        <input type="hidden" id="attachmentAddModal_id">
      </div>
      <div class="modal-body">
        <div class="form-horizontal" role="form">

          <div class="form-group">
            <div class="row">
              <div class="col-lg-4">
                <label class="" for="attachmentAddModal_filename">File name</label>
                <input type="text"  class="form-control small-padding" id="attachmentAddModal_filename">
              </div>
              <div class="col-lg-8">
                <label class="" for="attachmentAddModal_file">File</label>
                <input type="file"  class="form-control small-padding"  id="attachmentAddModal_file" >
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <label class="sr-only" for="attachmentAddModal_comment">Comment</label>
                <input type="text" class="form-control" id="attachmentAddModal_comment">
              </div>
            </div>
          </div>

          <div class="alert alert-danger" role="alert" id="attachmentAddModalAlert" style="display:none;">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <span class="sr-only">Error:</span>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default attachmentAddModal-close-modal">Close</button>
        <button type="button" class="btn btn-primary" id="attachmentAddModalApply">Apply</button>
      </div>
    </div>
  </div>
</div>
