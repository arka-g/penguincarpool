@extends ('template')

@section('content')
<h1>Login Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'msgaccept')) }}

<!-- 				<p>
					{{ Form::label('message', 'Message') }}
					{{ Form::text('Type Message') }}
				</p> -->
				<p>
					{{ Form::label('user_send_id', 'User Send ID') }}
					{{ Form::text('User send ID') }}
				</p>

				<p>{{ Form::submit('Submit!') }}</p>
			{{ Form::close() }}

		</div>
	</div>
@stop