@extends ('template')

@section('content')
<h1>Schedule Page</h1>
	<div class="container">
		<div class="row">
			{{ Form::open(array('url' => 'saveschedule')) }}

				<p>
					{{ Form::label('id', 'User ID') }}
					{{ Form::text('User ID') }}
				</p>

 				<p>
					{{ Form::label('date', 'Date') }}
					{{ Form::text('Date') }}
				</p>


				<p>
					{{ Form::label('time', 'Time') }}
					{{ Form::text('Time') }}
				</p>


				<p>
					{{ Form::label('pickup_location', 'Pickup Location') }}
					{{ Form::text('Pickup Location') }}
				</p>


				<p>
					{{ Form::label('dropoff_location', ' Destination') }}
					{{ Form::text(' destination') }}
				</p> 


				<p>{{ Form::submit('Submit!') }}</p>
			{{ Form::close() }}

		</div>
	</div>
@stop